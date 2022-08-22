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

package com.github.tommyettinger.kryo.digital;

import com.badlogic.gdx.math.RandomXS128;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.digital.Base;
import com.github.tommyettinger.digital.Hasher;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

public class DigitalTest {
    @Test
    public void testBase() {
        Kryo kryo = new Kryo();
        kryo.register(Base.class, new BaseSerializer());

        Base data = Base.scrambledBase(new RandomXS128(123456789L));

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Base data2 = kryo.readObject(input, Base.class);
            Assert.assertEquals(data.signed(0xFEDCBA9876543210L), data2.signed(0xFEDCBA9876543210L));
            Assert.assertEquals(data.unsigned(0xFEDCBA9876543210L), data.unsigned(0xFEDCBA9876543210L));
            Assert.assertEquals(data, data2);
        }
    }
    @Test
    public void testHasher() {
        Kryo kryo = new Kryo();
        kryo.register(Hasher.class, new HasherSerializer());

        long seed = Hasher.randomize3(System.nanoTime());
        Hasher data = new Hasher(seed);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Hasher data2 = kryo.readObject(input, Hasher.class);
            Assert.assertEquals(data.hash("0xFEDCBA9876543210L"), data2.hash("0xFEDCBA9876543210L"));
            Assert.assertEquals(data.hash64("0xFEDCBA9876543210L"), data2.hash64("0xFEDCBA9876543210L"));
        }
    }
}
