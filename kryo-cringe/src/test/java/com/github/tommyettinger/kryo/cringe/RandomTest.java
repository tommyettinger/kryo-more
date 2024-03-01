/*
 * Copyright (c) 2024 See AUTHORS file.
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
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.cringe.RandomAce320;
import com.github.tommyettinger.cringe.RandomDistinct64;
import com.github.tommyettinger.cringe.RandomXMX256;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

public class RandomTest {
    @Test
    public void testRandomDistinct64() {
        Kryo kryo = new Kryo();
        kryo.register(RandomDistinct64.class, new RandomDistinct64Serializer());

        RandomDistinct64 data = new RandomDistinct64(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            RandomDistinct64 data2 = kryo.readObject(input, RandomDistinct64.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testRandomAce320() {
        Kryo kryo = new Kryo();
        kryo.register(RandomAce320.class, new RandomAce320Serializer());

        RandomAce320 data = new RandomAce320(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            RandomAce320 data2 = kryo.readObject(input, RandomAce320.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testRandomXMX256() {
        Kryo kryo = new Kryo();
        kryo.register(RandomXMX256.class, new RandomXMX256Serializer());

        RandomXMX256 data = new RandomXMX256(-12345L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            RandomXMX256 data2 = kryo.readObject(input, RandomXMX256.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }
}
