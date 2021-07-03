/*
 * Copyright (c) 2020 - 2021 Legacy Fabric
 * Copyright (c) 2016 - 2021 FabricMC
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

package net.legacyfabric.fabric.api.util;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A useful tool in making cancellable events.
 *
 * <p>Similar to {@link ActionResult} but stores
 * a value as well.</p>
 */
public class TypedActionResult<T> {
	@NotNull
	private final ActionResult result;
	@Nullable
	private final T value;

	public TypedActionResult(@NotNull ActionResult result, @Nullable T value) {
		this.result = Objects.requireNonNull(result);
		this.value = value;
	}

	public @NotNull ActionResult getResult() {
		return this.result;
	}

	public @Nullable T getValue() {
		return this.value;
	}

	public static <T> TypedActionResult<T> success(@Nullable T data) {
		return new TypedActionResult<T>(ActionResult.SUCCESS, data);
	}

	public static <T> TypedActionResult<T> consume(@Nullable T data) {
		return new TypedActionResult<T>(ActionResult.CONSUME, data);
	}

	public static <T> TypedActionResult<T> pass(@Nullable T data) {
		return new TypedActionResult<T>(ActionResult.PASS, data);
	}

	public static <T> TypedActionResult<T> fail(@Nullable T data) {
		return new TypedActionResult<T>(ActionResult.FAIL, data);
	}

	public static <T> TypedActionResult<T> success(@Nullable T data, boolean swingHand) {
		return swingHand ? TypedActionResult.success(data) : TypedActionResult.consume(data);
	}
}
