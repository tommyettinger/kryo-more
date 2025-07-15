/*
 * Copyright (c) 2025 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.tommyettinger.kryo.libgdx;

import com.badlogic.gdx.math.Quaternion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link Quaternion}s.
 */
public class QuaternionSerializer extends Serializer<Quaternion> {

    public QuaternionSerializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final Quaternion data) {
        output.writeFloat(data.x);
        output.writeFloat(data.y);
        output.writeFloat(data.z);
        output.writeFloat(data.w);
    }

    @Override
    public Quaternion read(final Kryo kryo, final Input input, final Class<? extends Quaternion> dataClass) {
        return new Quaternion(input.readFloat(), input.readFloat(), input.readFloat(), input.readFloat());
    }

    @Override
    public Quaternion copy(Kryo kryo, Quaternion original) {
        return new Quaternion(original);
    }
}