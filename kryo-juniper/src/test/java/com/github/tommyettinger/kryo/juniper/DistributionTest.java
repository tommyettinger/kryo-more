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
import com.github.tommyettinger.kryo.juniper.distribution.*;
import com.github.tommyettinger.random.DistinctRandom;
import com.github.tommyettinger.random.distribution.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

public class DistributionTest {
    @Test
    public void testBernoulli() {
        Kryo kryo = new Kryo();
        kryo.register(DistinctRandom.class, new DistinctRandomSerializer());
        kryo.register(BernoulliDistribution.class, new BernoulliDistributionSerializer());

        BernoulliDistribution data = new BernoulliDistribution(new DistinctRandom(-12345L), 0.5);

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

    @Test
    public void testBeta() {
        Kryo kryo = new Kryo();
        kryo.register(DistinctRandom.class, new DistinctRandomSerializer());
        kryo.register(BetaDistribution.class, new BetaDistributionSerializer());

        BetaDistribution data = new BetaDistribution(new DistinctRandom(-12345L), 1.0, 1.0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            BetaDistribution data2 = kryo.readObject(input, BetaDistribution.class);
            Assert.assertEquals(data.nextDouble(), data2.nextDouble(), 0.0000001);
            Assert.assertEquals(data.nextDouble(), data2.nextDouble(), 0.0000001);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testBetaPrime() {
        Kryo kryo = new Kryo();
        kryo.register(DistinctRandom.class, new DistinctRandomSerializer());
        kryo.register(BetaPrimeDistribution.class, new BetaPrimeDistributionSerializer());

        BetaPrimeDistribution data = new BetaPrimeDistribution(new DistinctRandom(-12345L), 2.0, 2.0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            BetaPrimeDistribution data2 = kryo.readObject(input, BetaPrimeDistribution.class);
            Assert.assertEquals(data.nextDouble(), data2.nextDouble(), 0.0000001);
            Assert.assertEquals(data.nextDouble(), data2.nextDouble(), 0.0000001);
            Assert.assertEquals(data, data2);
        }
    }
    
    @Test
    public void testBinomial() {
        Kryo kryo = new Kryo();
        kryo.register(DistinctRandom.class, new DistinctRandomSerializer());
        kryo.register(BinomialDistribution.class, new BinomialDistributionSerializer());

        BinomialDistribution data = new BinomialDistribution(new DistinctRandom(-12345L), 0.5, 2);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            BinomialDistribution data2 = kryo.readObject(input, BinomialDistribution.class);
            Assert.assertEquals(data.nextDouble(), data2.nextDouble(), 0.0000001);
            Assert.assertEquals(data.nextDouble(), data2.nextDouble(), 0.0000001);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testCauchy() {
        Kryo kryo = new Kryo();
        kryo.register(DistinctRandom.class, new DistinctRandomSerializer());
        kryo.register(CauchyDistribution.class, new CauchyDistributionSerializer());

        CauchyDistribution data = new CauchyDistribution(new DistinctRandom(-12345L), 0.0, 1.0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            CauchyDistribution data2 = kryo.readObject(input, CauchyDistribution.class);
            Assert.assertEquals(data.nextDouble(), data2.nextDouble(), 0.0000001);
            Assert.assertEquals(data.nextDouble(), data2.nextDouble(), 0.0000001);
            Assert.assertEquals(data, data2);
        }
    }

}
