/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.fabric.api.networking.v1.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import net.minecraft.util.PacketByteBuf;

public final class PacketByteBufs {
	private static final PacketByteBuf EMPTY_PACKET_BYTE_BUF = new PacketByteBuf(Unpooled.EMPTY_BUFFER);

	private PacketByteBufs() {
	}

	public static PacketByteBuf empty() {
		return EMPTY_PACKET_BYTE_BUF;
	}

	public static PacketByteBuf create() {
		return new PacketByteBuf(Unpooled.buffer());
	}

	public static PacketByteBuf readBytes(ByteBuf buf, int length) {
		return new PacketByteBuf(buf.readBytes(length));
	}

	public static PacketByteBuf readSlice(ByteBuf buf, int length) {
		return new PacketByteBuf(buf.readSlice(length));
	}

	public static PacketByteBuf readRetainedSlice(ByteBuf buf, int length) {
		return new PacketByteBuf(buf.readSlice(length));
	}

	public static PacketByteBuf copy(ByteBuf buf) {
		return new PacketByteBuf(buf.copy());
	}

	public static PacketByteBuf copy(ByteBuf buf, int index, int length) {
		return new PacketByteBuf(buf.copy(index, length));
	}


	public static PacketByteBuf slice(ByteBuf buf) {
		return new PacketByteBuf(buf.slice());
	}

	public static PacketByteBuf retainedSlice(ByteBuf buf) {
		return new PacketByteBuf(buf.slice());
	}

	public static PacketByteBuf slice(ByteBuf buf, int index, int length) {
		return new PacketByteBuf(buf.slice(index, length));
	}

	public static PacketByteBuf retainedSlice(ByteBuf buf, int index, int length) {
		return new PacketByteBuf(buf.slice(index, length));
	}

	public static PacketByteBuf duplicate(ByteBuf buf) {
		return new PacketByteBuf(buf.duplicate());
	}

	public static PacketByteBuf retainedDuplicate(ByteBuf buf) {
		return new PacketByteBuf(buf.duplicate());
	}
}
