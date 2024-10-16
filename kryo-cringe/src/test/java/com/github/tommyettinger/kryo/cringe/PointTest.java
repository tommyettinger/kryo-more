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
import com.github.tommyettinger.cringe.PointSequence.*;
import com.github.tommyettinger.kryo.cringe.PointSequenceSerializers.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

public class PointTest {
    @Test
    public void testHalton2() {
        Kryo kryo = new Kryo();
        kryo.register(Halton2.class, new Halton2Serializer());

        Halton2 data = new Halton2(2, 3, 100);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Halton2 data2 = kryo.readObject(input, Halton2.class);
            Assert.assertEquals(data.next(), data2.next());
            Assert.assertEquals(data.next(), data2.next());
            // point sequences don't implement equals() currently
            Assert.assertEquals(data.baseX, data2.baseX);
            Assert.assertEquals(data.baseY, data2.baseY);
            Assert.assertEquals(data.index, data2.index);
        }
    }
    @Test
    public void testHalton3() {
        Kryo kryo = new Kryo();
        kryo.register(Halton3.class, new Halton3Serializer());

        Halton3 data = new Halton3(2, 3, 5, 100);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Halton3 data2 = kryo.readObject(input, Halton3.class);
            Assert.assertEquals(data.next(), data2.next());
            Assert.assertEquals(data.next(), data2.next());
            // point sequences don't implement equals() currently
            Assert.assertEquals(data.baseX, data2.baseX);
            Assert.assertEquals(data.baseY, data2.baseY);
            Assert.assertEquals(data.baseZ, data2.baseZ);
            Assert.assertEquals(data.index, data2.index);
        }
    }
}
