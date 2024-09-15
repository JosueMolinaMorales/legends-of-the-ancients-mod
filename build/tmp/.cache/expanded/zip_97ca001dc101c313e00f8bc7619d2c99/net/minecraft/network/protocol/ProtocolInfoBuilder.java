package net.minecraft.network.protocol;

import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.network.ClientboundPacketListener;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.PacketListener;
import net.minecraft.network.ProtocolInfo;
import net.minecraft.network.ServerboundPacketListener;
import net.minecraft.network.codec.StreamCodec;

public class ProtocolInfoBuilder<T extends PacketListener, B extends ByteBuf> {
    private final ConnectionProtocol protocol;
    private final PacketFlow flow;
    private final List<ProtocolInfoBuilder.CodecEntry<T, ?, B>> codecs = new ArrayList<>();
    @Nullable
    private BundlerInfo bundlerInfo;

    public ProtocolInfoBuilder(ConnectionProtocol pProtocol, PacketFlow pFlow) {
        this.protocol = pProtocol;
        this.flow = pFlow;
    }

    public <P extends Packet<? super T>> ProtocolInfoBuilder<T, B> addPacket(PacketType<P> pType, StreamCodec<? super B, P> pSerializer) {
        this.codecs.add(new ProtocolInfoBuilder.CodecEntry<>(pType, pSerializer));
        return this;
    }

    public <P extends BundlePacket<? super T>, D extends BundleDelimiterPacket<? super T>> ProtocolInfoBuilder<T, B> withBundlePacket(
        PacketType<P> pType, Function<Iterable<Packet<? super T>>, P> pBundler, D pPacket
    ) {
        StreamCodec<ByteBuf, D> streamcodec = StreamCodec.unit(pPacket);
        PacketType<D> packettype = (PacketType)pPacket.type();
        this.codecs.add(new ProtocolInfoBuilder.CodecEntry<>(packettype, streamcodec));
        this.bundlerInfo = BundlerInfo.createForPacket(pType, pBundler, pPacket);
        return this;
    }

    private StreamCodec<ByteBuf, Packet<? super T>> buildPacketCodec(Function<ByteBuf, B> pBufferFactory, List<ProtocolInfoBuilder.CodecEntry<T, ?, B>> pCodecs) {
        ProtocolCodecBuilder<ByteBuf, T> protocolcodecbuilder = new ProtocolCodecBuilder<>(this.flow);

        for (ProtocolInfoBuilder.CodecEntry<T, ?, B> codecentry : pCodecs) {
            codecentry.addToBuilder(protocolcodecbuilder, pBufferFactory);
        }

        return protocolcodecbuilder.build();
    }

    public ProtocolInfo<T> build(Function<ByteBuf, B> pBufferFactory) {
        return new ProtocolInfoBuilder.Implementation<>(this.protocol, this.flow, this.buildPacketCodec(pBufferFactory, this.codecs), this.bundlerInfo);
    }

    public ProtocolInfo.Unbound<T, B> buildUnbound() {
        List<ProtocolInfoBuilder.CodecEntry<T, ?, B>> list = List.copyOf(this.codecs);
        BundlerInfo bundlerinfo = this.bundlerInfo;
        return p_334608_ -> new ProtocolInfoBuilder.Implementation<>(this.protocol, this.flow, this.buildPacketCodec(p_334608_, list), bundlerinfo);
    }

    private static <L extends PacketListener> ProtocolInfo<L> protocol(
        ConnectionProtocol pProtocol, PacketFlow pFlow, Consumer<ProtocolInfoBuilder<L, FriendlyByteBuf>> p_327993_
    ) {
        ProtocolInfoBuilder<L, FriendlyByteBuf> protocolinfobuilder = new ProtocolInfoBuilder<>(pProtocol, pFlow);
        p_327993_.accept(protocolinfobuilder);
        return protocolinfobuilder.build(FriendlyByteBuf::new);
    }

    public static <T extends ServerboundPacketListener> ProtocolInfo<T> serverboundProtocol(
        ConnectionProtocol pProtocol, Consumer<ProtocolInfoBuilder<T, FriendlyByteBuf>> pBuilder
    ) {
        return protocol(pProtocol, PacketFlow.SERVERBOUND, pBuilder);
    }

    public static <T extends ClientboundPacketListener> ProtocolInfo<T> clientboundProtocol(
        ConnectionProtocol pProtocol, Consumer<ProtocolInfoBuilder<T, FriendlyByteBuf>> pBuilder
    ) {
        return protocol(pProtocol, PacketFlow.CLIENTBOUND, pBuilder);
    }

    private static <L extends PacketListener, B extends ByteBuf> ProtocolInfo.Unbound<L, B> protocolUnbound(
        ConnectionProtocol pProtocol, PacketFlow pFlow, Consumer<ProtocolInfoBuilder<L, B>> pBuilder
    ) {
        ProtocolInfoBuilder<L, B> protocolinfobuilder = new ProtocolInfoBuilder<>(pProtocol, pFlow);
        pBuilder.accept(protocolinfobuilder);
        return protocolinfobuilder.buildUnbound();
    }

    public static <T extends ServerboundPacketListener, B extends ByteBuf> ProtocolInfo.Unbound<T, B> serverboundProtocolUnbound(
        ConnectionProtocol pProtocol, Consumer<ProtocolInfoBuilder<T, B>> pBuilder
    ) {
        return protocolUnbound(pProtocol, PacketFlow.SERVERBOUND, pBuilder);
    }

    public static <T extends ClientboundPacketListener, B extends ByteBuf> ProtocolInfo.Unbound<T, B> clientboundProtocolUnbound(
        ConnectionProtocol pProtocol, Consumer<ProtocolInfoBuilder<T, B>> pBuilder
    ) {
        return protocolUnbound(pProtocol, PacketFlow.CLIENTBOUND, pBuilder);
    }

    static record CodecEntry<T extends PacketListener, P extends Packet<? super T>, B extends ByteBuf>(
        PacketType<P> type, StreamCodec<? super B, P> serializer
    ) {
        public void addToBuilder(ProtocolCodecBuilder<ByteBuf, T> pCodecBuilder, Function<ByteBuf, B> pBufferFactory) {
            StreamCodec<ByteBuf, P> streamcodec = this.serializer.mapStream(pBufferFactory);
            pCodecBuilder.add(this.type, streamcodec);
        }
    }

    static record Implementation<L extends PacketListener>(
        ConnectionProtocol id, PacketFlow flow, StreamCodec<ByteBuf, Packet<? super L>> codec, @Nullable BundlerInfo bundlerInfo
    ) implements ProtocolInfo<L> {
        @Nullable
        @Override
        public BundlerInfo bundlerInfo() {
            return this.bundlerInfo;
        }

        @Override
        public ConnectionProtocol id() {
            return this.id;
        }

        @Override
        public PacketFlow flow() {
            return this.flow;
        }

        @Override
        public StreamCodec<ByteBuf, Packet<? super L>> codec() {
            return this.codec;
        }
    }
}