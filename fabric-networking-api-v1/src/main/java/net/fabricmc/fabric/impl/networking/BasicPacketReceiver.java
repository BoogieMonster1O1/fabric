package net.fabricmc.fabric.impl.networking;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.networking.v1.ChannelHandler;
import net.fabricmc.fabric.api.networking.v1.ListenerContext;
import net.fabricmc.fabric.api.networking.v1.PacketReceiver;

public final class BasicPacketReceiver<C extends ListenerContext> implements PacketReceiver<C> {
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private final Map<Identifier, ChannelHandler<? super C>> handlers;

	public BasicPacketReceiver() {
		this(new HashMap<>()); // sync map should be fine as there is little read write competitions
	}

	public BasicPacketReceiver(Map<Identifier, ChannelHandler<? super C>> map) {
		this.handlers = map;
	}

	public ChannelHandler<? super C> get(Identifier channel) {
		Lock lock = this.lock.readLock();
		lock.lock();

		try {
			return this.handlers.get(channel);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean register(Identifier channel, ChannelHandler<? super C> handler) {
		Objects.requireNonNull(handler, "handler");
		Lock lock = this.lock.writeLock();
		lock.lock();

		try {
			return this.handlers.putIfAbsent(channel, handler) == null;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public ChannelHandler<? super C> unregister(Identifier channel) {
		Lock lock = this.lock.writeLock();
		lock.lock();

		try {
			return this.handlers.remove(channel);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public Collection<Identifier> getChannels() {
		Lock lock = this.lock.readLock();
		lock.lock();

		try {
			return new HashSet<>(this.handlers.keySet());
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean hasChannel(Identifier channel) {
		Lock lock = this.lock.readLock();
		lock.lock();

		try {
			return this.handlers.containsKey(channel);
		} finally {
			lock.unlock();
		}
	}
}
