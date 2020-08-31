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

package net.fabricmc.fabric.api.command.v1;

import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.OperatorEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class ServerCommandSource {
	private final CommandSource source;
	private final Vec3d position;
	private final ServerWorld world;
	private final Entity entity;
	private final int permissionLevel;

	public ServerCommandSource(CommandSource source, Vec3d position, ServerWorld world, Entity entity, int permissionLevel) {
		this.source = source;
		this.position = position;
		this.world = world;
		this.entity = entity;
		this.permissionLevel = permissionLevel;
	}

	public CommandSource getSource() {
		return source;
	}

	public Vec3d getPosition() {
		return position;
	}

	public ServerWorld getWorld() {
		return world;
	}

	public Entity getEntity() {
		return entity;
	}

	public int getPermissionLevel() {
		return permissionLevel;
	}

	public boolean isPlayer() {
		return entity instanceof ServerPlayerEntity;
	}

	public boolean hasPermissionLevel(int level) {
		return permissionLevel <= level;
	}

	public ServerCommandSource withPosition(Vec3d position) {
		return new ServerCommandSource(source, position, world, entity, permissionLevel);
	}

	public ServerCommandSource withWorld(ServerWorld world) {
		return new ServerCommandSource(source, position, world, entity, permissionLevel);
	}

	public ServerCommandSource withEntity(Entity entity) {
		return new ServerCommandSource(source, position, world, entity, permissionLevel);
	}

	public ServerCommandSource withPermission(int permissionLevel) {
		return new ServerCommandSource(source, position, world, entity, permissionLevel);
	}

	public void sendFeedback(Text text) {
		if (source.sendCommandFeedback()) {
			source.sendMessage(text);
		}
	}

	public static ServerCommandSource from(CommandSource source) {
		int permissionLevel = 0;

		if (source.getEntity() instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) source.getEntity();
			OperatorEntry operatorEntry = player.server.getPlayerManager().getOpList().get(player.getGameProfile());

			if (operatorEntry != null) {
				permissionLevel = operatorEntry.getPermissionLevel();
			}
		}

		return new ServerCommandSource(source, source.getPos(), (ServerWorld) source.getWorld(), source.getEntity(), permissionLevel);
	}
}
