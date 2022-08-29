/*
 * Copyright (c) 2022 See AUTHORS file.
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

package com.github.tommyettinger.kryo.juniper;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.random.EnhancedRandom;
import com.github.tommyettinger.random.distribution.BernoulliDistribution;

public class BernoulliDistributionSerializer extends Serializer<BernoulliDistribution> {
    public BernoulliDistributionSerializer() {
        super(false, false);
    }

    @Override
    public BernoulliDistribution copy(Kryo kryo, BernoulliDistribution original) {
        return original.copy();
    }

    @Override
    public void write(Kryo kryo, Output output, BernoulliDistribution object) {
        kryo.writeClassAndObject(output, object.generator);
        output.writeDouble(object.getParameterA());
    }

    @Override
    public BernoulliDistribution read(Kryo kryo, Input input, Class<? extends BernoulliDistribution> type) {
        return new BernoulliDistribution((EnhancedRandom) kryo.readClassAndObject(input), input.readDouble());
    }
}
