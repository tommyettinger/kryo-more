/*
 * Copyright (c) 2020-2024 See AUTHORS file.
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

package com.github.tommyettinger.kryo.cringe;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.cringe.ContinuousNoise;
import com.github.tommyettinger.cringe.RawNoise;

/**
 * Serializer for {@link ContinuousNoise}; needs whatever RawNoise implementation this uses to be registered as well.
 */
public class ContinuousNoiseSerializer extends Serializer<ContinuousNoise> {
    public ContinuousNoiseSerializer() {
        setImmutable(false);
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final ContinuousNoise data) {
        kryo.writeClassAndObject(output, data.getWrapped());
        output.writeInt(data.getSeed(), false);
        output.writeFloat(data.getFrequency());
        output.writeInt(data.getMode(), true);
        output.writeInt(data.getFractalOctaves(), true);
    }

    @Override
    public ContinuousNoise read(final Kryo kryo, final Input input, final Class<? extends ContinuousNoise> dataClass) {
        return new ContinuousNoise((RawNoise) kryo.readClassAndObject(input), input.readInt(false), input.readFloat(),
                input.readInt(true), input.readInt(true));
    }

    @Override
    public ContinuousNoise copy(Kryo kryo, ContinuousNoise original) {
        return new ContinuousNoise(original);
    }
}
