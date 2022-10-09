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
import com.github.tommyettinger.kryo.juniper.distribution.*;
import com.github.tommyettinger.random.*;
import com.github.tommyettinger.random.distribution.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.IdentityHashMap;
import java.util.Map;

public class DistributionTest {
    @Test
    public void testDistributions() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        IdentityHashMap<Class<? extends Distribution>, Class<? extends Serializer<? extends Distribution>>>
                mapping = new IdentityHashMap<>();
        mapping.put(ArcsineDistribution.class, ArcsineDistributionSerializer.class);
        mapping.put(BernoulliDistribution.class, BernoulliDistributionSerializer.class);
        mapping.put(BetaDistribution.class, BetaDistributionSerializer.class);
        mapping.put(BetaPrimeDistribution.class, BetaPrimeDistributionSerializer.class);
        mapping.put(BinomialDistribution.class, BinomialDistributionSerializer.class);
        mapping.put(CauchyDistribution.class, CauchyDistributionSerializer.class);
        mapping.put(ChiDistribution.class, ChiDistributionSerializer.class);
        mapping.put(ChiSquareDistribution.class, ChiSquareDistributionSerializer.class);
        mapping.put(ContinuousUniformDistribution.class, ContinuousUniformDistributionSerializer.class);
        mapping.put(DiscreteUniformDistribution.class, DiscreteUniformDistributionSerializer.class);
        mapping.put(ErlangDistribution.class, ErlangDistributionSerializer.class);
        mapping.put(ExponentialDistribution.class, ExponentialDistributionSerializer.class);
        mapping.put(FisherSnedecorDistribution.class, FisherSnedecorDistributionSerializer.class);
        mapping.put(FisherTippettDistribution.class, FisherTippettDistributionSerializer.class);
        mapping.put(GammaDistribution.class, GammaDistributionSerializer.class);
        mapping.put(GeometricDistribution.class, GeometricDistributionSerializer.class);
        mapping.put(KumaraswamyDistribution.class, KumaraswamyDistributionSerializer.class);
        mapping.put(LaplaceDistribution.class, LaplaceDistributionSerializer.class);
        mapping.put(LogCauchyDistribution.class, LogCauchyDistributionSerializer.class);
        mapping.put(LogisticDistribution.class, LogisticDistributionSerializer.class);
        mapping.put(LogNormalDistribution.class, LogNormalDistributionSerializer.class);
        mapping.put(LumpDistribution.class, LumpDistributionSerializer.class);
        mapping.put(NormalDistribution.class, NormalDistributionSerializer.class);
        mapping.put(ParetoDistribution.class, ParetoDistributionSerializer.class);
        mapping.put(PoissonDistribution.class, PoissonDistributionSerializer.class);
        mapping.put(PowerDistribution.class, PowerDistributionSerializer.class);
        mapping.put(RayleighDistribution.class, RayleighDistributionSerializer.class);
        mapping.put(StudentsTDistribution.class, StudentsTDistributionSerializer.class);
        mapping.put(TriangularDistribution.class, TriangularDistributionSerializer.class);
        mapping.put(WeibullDistribution.class, WeibullDistributionSerializer.class);
        mapping.put(ZipfianDistribution.class, ZipfianDistributionSerializer.class);

        for (Map.Entry<Class<? extends Distribution>, Class<? extends Serializer<? extends Distribution>>> ent : mapping.entrySet()) {
            Kryo kryo = new Kryo();
            kryo.register(WhiskerRandom.class, new WhiskerRandomSerializer());
            kryo.register(ent.getKey(), ent.getValue().getDeclaredConstructor().newInstance());

            Distribution data = ent.getKey().getDeclaredConstructor().newInstance();

            ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
            Output output = new Output(baos);
            kryo.writeObject(output, data);
            byte[] bytes = output.toBytes();
            try (Input input = new Input(bytes)) {
                Distribution data2 = kryo.readObject(input, ent.getKey());
                Assert.assertEquals(data.nextDouble(), data2.nextDouble(), 0.0000001);
                Assert.assertEquals(data.nextDouble(), data2.nextDouble(), 0.0000001);
                Assert.assertEquals(data, data2);
            }
        }
    }

    @Test
    public void testDistribution() {
        Kryo kryo = new Kryo();
        DistributionSerializer ser = new DistributionSerializer();
        kryo.register(Distribution.class, ser);

        Distribution data = new KumaraswamyDistribution(new DistinctRandom(123L), 2.5, 2.0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data, ser);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Distribution data2 = kryo.readObject(input, Distribution.class);
            Assert.assertEquals(data.nextDouble(), data2.nextDouble(), 0x1p-32);
            Assert.assertEquals(data.nextDouble(), data2.nextDouble(), 0x1p-32);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testDistributedRandom() {
        Kryo kryo = new Kryo();
//        DistributedRandomSerializer ser = new DistributedRandomSerializer();
        kryo.register(DistributedRandom.class, new DistributedRandomSerializer());

        DistributedRandom random = new DistributedRandom(
                new KumaraswamyDistribution(new DistinctRandom(123L), 2.5, 2.0), DistributedRandom.ReductionMode.FRACTION);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, random);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            DistributedRandom data2 = kryo.readObject(input, DistributedRandom.class);
            Assert.assertEquals(random.nextDouble(), data2.nextDouble(), 0x1p-32);
            Assert.assertEquals(random.nextDouble(), data2.nextDouble(), 0x1p-32);
            Assert.assertTrue(EnhancedRandom.areEqual(random, data2));
        }
    }

    /* This is the old format the tests used.
    @Test
    public void testBernoulli() {
        Kryo kryo = new Kryo();
        kryo.register(WhiskerRandom.class, new WhiskerRandomSerializer());
        kryo.register(BernoulliDistribution.class, new BernoulliDistributionSerializer());

        BernoulliDistribution data = new BernoulliDistribution();

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
     */
}
