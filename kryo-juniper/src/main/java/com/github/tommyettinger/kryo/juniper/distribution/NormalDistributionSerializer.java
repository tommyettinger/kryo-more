/*
 * Copyright (c) 2022-2024 See AUTHORS file.
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

package com.github.tommyettinger.kryo.juniper.distribution;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.random.EnhancedRandom;
import com.github.tommyettinger.random.distribution.NormalDistribution;

public class NormalDistributionSerializer extends Serializer<NormalDistribution> {
    public NormalDistributionSerializer() {
        super(false, false);
    }

    @Override
    public NormalDistribution copy(Kryo kryo, NormalDistribution original) {
        return original.copy();
    }

    @Override
    public void write(Kryo kryo, Output output, NormalDistribution object) {
        kryo.writeClassAndObject(output, object.generator);
        output.writeDouble(object.getParameterA());
        output.writeDouble(object.getParameterB());
    }

    @Override
    public NormalDistribution read(Kryo kryo, Input input, Class<? extends NormalDistribution> type) {
        return new NormalDistribution((EnhancedRandom) kryo.readClassAndObject(input),
                input.readDouble(), input.readDouble());
    }
}
