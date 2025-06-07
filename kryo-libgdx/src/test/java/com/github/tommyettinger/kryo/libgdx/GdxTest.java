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

package com.github.tommyettinger.kryo.libgdx;

import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.utils.ArrayMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.junit.Assert;
import org.junit.Test;

public class GdxTest {
    @Test
    public void testAffine2() {
        Kryo kryo = new Kryo();
        kryo.register(Affine2.class, new Affine2Serializer());

        Affine2 data = new Affine2();

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Affine2 data2 = kryo.readObject(input, Affine2.class);
            // Affine2 does not implement equals().
//            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.m00, data2.m00, 0.00001f);
            Assert.assertEquals(data.m01, data2.m01, 0.00001f);
            Assert.assertEquals(data.m02, data2.m02, 0.00001f);
            Assert.assertEquals(data.m10, data2.m10, 0.00001f);
            Assert.assertEquals(data.m11, data2.m11, 0.00001f);
            Assert.assertEquals(data.m12, data2.m12, 0.00001f);
        }
    }
    @Test
    public void testArrayMap() {
        Kryo kryo = new Kryo();
        kryo.register(ArrayMap.class, new ArrayMapSerializer());

        ArrayMap<String, Integer> data = new ArrayMap<>();
        data.put("Cthulhu", -123456);
        data.put("lies", Integer.MIN_VALUE);
        data.put("deep", 456789012);
        data.put("in", 0);
        data.put("Rl'yeh", 1111);
        data.put("dreaming", 1);
        data.put("of", -1);
        data.put("waffles", 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ArrayMap<?,?> data2 = kryo.readObject(input, ArrayMap.class);
            Assert.assertEquals(data, data2);
        }
    }
}
