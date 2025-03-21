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
import com.github.tommyettinger.cringe.Vector5;
import com.github.tommyettinger.cringe.Vector6;
import com.github.tommyettinger.kryo.cringe.PointSequenceSerializers.*;
import org.junit.Assert;
import org.junit.Test;

public class PointTest {
    @Test
    public void testHalton2() {
        Kryo kryo = new Kryo();
        kryo.register(Halton2.class, new Halton2Serializer());

        Halton2 data = new Halton2(2, 3, 100);

        Output output = new Output(32, -1);
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

        Output output = new Output(32, -1);
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
    
    @Test
    public void testHalton4() {
        Kryo kryo = new Kryo();
        kryo.register(Halton4.class, new Halton4Serializer());

        Halton4 data = new Halton4(2, 3, 5, 7, 100);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Halton4 data2 = kryo.readObject(input, Halton4.class);
            Assert.assertEquals(data.next(), data2.next());
            Assert.assertEquals(data.next(), data2.next());
            // point sequences don't implement equals() currently
            Assert.assertEquals(data.baseX, data2.baseX);
            Assert.assertEquals(data.baseY, data2.baseY);
            Assert.assertEquals(data.baseZ, data2.baseZ);
            Assert.assertEquals(data.baseW, data2.baseW);
            Assert.assertEquals(data.index, data2.index);
        }
    }
    
    @Test
    public void testHalton5() {
        Kryo kryo = new Kryo();
        kryo.register(Halton5.class, new Halton5Serializer());

        Halton5 data = new Halton5(2, 3, 5, 7, 11, 100);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Halton5 data2 = kryo.readObject(input, Halton5.class);
            Assert.assertEquals(data.next(), data2.next());
            Assert.assertEquals(data.next(), data2.next());
            // point sequences don't implement equals() currently
            Assert.assertEquals(data.baseX, data2.baseX);
            Assert.assertEquals(data.baseY, data2.baseY);
            Assert.assertEquals(data.baseZ, data2.baseZ);
            Assert.assertEquals(data.baseW, data2.baseW);
            Assert.assertEquals(data.baseU, data2.baseU);
            Assert.assertEquals(data.index, data2.index);
        }
    }
    
    @Test
    public void testHalton6() {
        Kryo kryo = new Kryo();
        kryo.register(Halton6.class, new Halton6Serializer());

        Halton6 data = new Halton6(2, 3, 5, 7, 11, 13, 100);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Halton6 data2 = kryo.readObject(input, Halton6.class);
            Assert.assertEquals(data.next(), data2.next());
            Assert.assertEquals(data.next(), data2.next());
            // point sequences don't implement equals() currently
            Assert.assertEquals(data.baseX, data2.baseX);
            Assert.assertEquals(data.baseY, data2.baseY);
            Assert.assertEquals(data.baseZ, data2.baseZ);
            Assert.assertEquals(data.baseW, data2.baseW);
            Assert.assertEquals(data.baseU, data2.baseU);
            Assert.assertEquals(data.baseV, data2.baseV);
            Assert.assertEquals(data.index, data2.index);
        }
    }

    @Test
    public void testR2() {
        Kryo kryo = new Kryo();
        kryo.register(R2.class, new R2Serializer());

        R2 data = new R2(0.1f, 0.2f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            R2 data2 = kryo.readObject(input, R2.class);
            Assert.assertEquals(data.next(), data2.next());
            Assert.assertEquals(data.next(), data2.next());
            // point sequences don't implement equals() currently
            Assert.assertEquals(data.x, data2.x, Float.MIN_NORMAL);
            Assert.assertEquals(data.y, data2.y, Float.MIN_NORMAL);
        }
    }

    @Test
    public void testR3() {
        Kryo kryo = new Kryo();
        kryo.register(R3.class, new R3Serializer());

        R3 data = new R3(0.1f, 0.2f, 0.3f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            R3 data2 = kryo.readObject(input, R3.class);
            Assert.assertEquals(data.next(), data2.next());
            Assert.assertEquals(data.next(), data2.next());
            // point sequences don't implement equals() currently
            Assert.assertEquals(data.x, data2.x, Float.MIN_NORMAL);
            Assert.assertEquals(data.y, data2.y, Float.MIN_NORMAL);
            Assert.assertEquals(data.z, data2.z, Float.MIN_NORMAL);
        }
    }

    @Test
    public void testR4() {
        Kryo kryo = new Kryo();
        kryo.register(R4.class, new R4Serializer());

        R4 data = new R4(0.1f, 0.2f, 0.3f, 0.4f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            R4 data2 = kryo.readObject(input, R4.class);
            Assert.assertEquals(data.next(), data2.next());
            Assert.assertEquals(data.next(), data2.next());
            // point sequences don't implement equals() currently
            Assert.assertEquals(data.x, data2.x, Float.MIN_NORMAL);
            Assert.assertEquals(data.y, data2.y, Float.MIN_NORMAL);
            Assert.assertEquals(data.z, data2.z, Float.MIN_NORMAL);
        }
    }

    @Test
    public void testR5() {
        Kryo kryo = new Kryo();
        kryo.register(R5.class, new R5Serializer());

        R5 data = new R5(0.1f, 0.2f, 0.3f, 0.4f, 0.5f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            R5 data2 = kryo.readObject(input, R5.class);
            Assert.assertEquals(data.next(), data2.next());
            Assert.assertEquals(data.next(), data2.next());
            // point sequences don't implement equals() currently
            Assert.assertEquals(data.x, data2.x, Float.MIN_NORMAL);
            Assert.assertEquals(data.y, data2.y, Float.MIN_NORMAL);
            Assert.assertEquals(data.z, data2.z, Float.MIN_NORMAL);
        }
    }

    @Test
    public void testR6() {
        Kryo kryo = new Kryo();
        kryo.register(R6.class, new R6Serializer());

        R6 data = new R6(0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            R6 data2 = kryo.readObject(input, R6.class);
            Assert.assertEquals(data.next(), data2.next());
            Assert.assertEquals(data.next(), data2.next());
            // point sequences don't implement equals() currently
            Assert.assertEquals(data.x, data2.x, Float.MIN_NORMAL);
            Assert.assertEquals(data.y, data2.y, Float.MIN_NORMAL);
            Assert.assertEquals(data.z, data2.z, Float.MIN_NORMAL);
        }
    }
    
    @Test
    public void testVector5() {
        Kryo kryo = new Kryo();
        kryo.register(Vector5.class, new Vector5Serializer());

        Vector5 data = new Vector5(0.1f, 0.2f, 0.3f, 0.4f, 0.5f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Vector5 data2 = kryo.readObject(input, Vector5.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testVector6() {
        Kryo kryo = new Kryo();
        kryo.register(Vector6.class, new Vector6Serializer());

        Vector6 data = new Vector6(0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Vector6 data2 = kryo.readObject(input, Vector6.class);
            Assert.assertEquals(data, data2);
        }
    }
}
