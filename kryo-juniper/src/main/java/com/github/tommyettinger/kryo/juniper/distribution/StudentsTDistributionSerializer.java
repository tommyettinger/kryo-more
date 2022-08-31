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

package com.github.tommyettinger.kryo.juniper.distribution;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.random.EnhancedRandom;
import com.github.tommyettinger.random.distribution.StudentsTDistribution;

public class StudentsTDistributionSerializer extends Serializer<StudentsTDistribution> {
    public StudentsTDistributionSerializer() {
        super(false, false);
    }

    @Override
    public StudentsTDistribution copy(Kryo kryo, StudentsTDistribution original) {
        return original.copy();
    }

    @Override
    public void write(Kryo kryo, Output output, StudentsTDistribution object) {
        kryo.writeClassAndObject(output, object.generator);
        output.writeDouble(object.getParameterA());
    }

    @Override
    public StudentsTDistribution read(Kryo kryo, Input input, Class<? extends StudentsTDistribution> type) {
        return new StudentsTDistribution((EnhancedRandom) kryo.readClassAndObject(input), input.readDouble());
    }
}
