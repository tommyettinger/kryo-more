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
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.random.*;
import com.github.tommyettinger.random.distribution.BernoulliDistribution;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

public class DistributionTest {
    @Test
    public void testEnhancedRandom() {
        Kryo kryo = new Kryo();
        kryo.register(Xoshiro128PlusPlusRandom.class, new Xoshiro128PlusPlusRandomSerializer());
        kryo.register(BernoulliDistribution.class, new BernoulliDistributionSerializer());

        BernoulliDistribution data = new BernoulliDistribution(new Xoshiro128PlusPlusRandom(-12345L), 0.5);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            BernoulliDistribution data2 = kryo.readObject(input, BernoulliDistribution.class);
            Assert.assertEquals(data.nextDouble(), data2.nextDouble(), 0.0000001);
            Assert.assertEquals(data.nextDouble(), data2.nextDouble(), 0.0000001);
            Assert.assertEquals(data, data2);
        }
    }
}
