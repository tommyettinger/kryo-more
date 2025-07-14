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

package com.github.tommyettinger.kryo.jdkgdxds;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.github.tommyettinger.ds.NumberedSet;

public class NumberedSetSerializer extends CollectionSerializer<NumberedSet<?>> {
    public NumberedSetSerializer() {
        super();
        setElementsCanBeNull(false);
    }

    @Override
    protected void writeHeader(Kryo kryo, Output output, NumberedSet<?> data) {
        output.writeInt(data.getDefaultValue());
    }

    @Override
    protected NumberedSet<?> create(Kryo kryo, Input input, Class<? extends NumberedSet<?>> type, int size) {
        NumberedSet<?> data = new NumberedSet<>(size);
        data.setDefaultValue(input.readInt());
        return data;
    }

    @Override
    protected NumberedSet<?> createCopy(Kryo kryo, NumberedSet<?> original) {
        NumberedSet<?> data = new NumberedSet<>(original.size(), original.getLoadFactor());
        data.setDefaultValue(original.getDefaultValue());
        return data;
    }
}
