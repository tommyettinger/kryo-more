/*
 * Copyright (c) 2023-2024 See AUTHORS file.
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
import com.esotericsoftware.kryo.serializers.MapSerializer;
import com.github.tommyettinger.ds.FilteredIterableOrderedMap;
import com.github.tommyettinger.ds.OrderType;
import com.github.tommyettinger.ds.Utilities;
import com.github.tommyettinger.function.ObjPredicate;
import com.github.tommyettinger.function.ObjToSameFunction;

import java.util.NoSuchElementException;

public class FilteredIterableOrderedMapSerializer extends MapSerializer<FilteredIterableOrderedMap<?, ?, ?>> {

    private static final OrderType[] ORDER_TYPES = OrderType.values();

    public FilteredIterableOrderedMapSerializer() {
        super();
        setKeysCanBeNull(false);
    }

    @Override
    protected void writeHeader(Kryo kryo, Output output, FilteredIterableOrderedMap<?, ?, ?> data) {
        ObjPredicate<?> filter = data.getFilter();
        ObjToSameFunction<?> editor = data.getEditor();
        if(filter == null || editor == null)
            throw new NoSuchElementException("A FilteredIterableOrderedMap must have a non-null filter and editor to be serialized.");
        if(kryo.getClassResolver().getRegistration(filter.getClass()) == null)
            kryo.register(filter.getClass());
        if(kryo.getClassResolver().getRegistration(editor.getClass()) == null)
            kryo.register(editor.getClass());
        kryo.writeClassAndObject(output, filter);
        kryo.writeClassAndObject(output, editor);
        output.writeVarInt(data.getOrderType().ordinal(), true);
        kryo.writeClassAndObject(output, data.getDefaultValue());
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    protected FilteredIterableOrderedMap<?, ?, ?> create(Kryo kryo, Input input, Class<? extends FilteredIterableOrderedMap<?, ?, ?>> type, int size) {
        FilteredIterableOrderedMap data = new FilteredIterableOrderedMap((ObjPredicate<?>)kryo.readClassAndObject(input),
                (ObjToSameFunction<?>) kryo.readClassAndObject(input), size, Utilities.getDefaultLoadFactor(),
                ORDER_TYPES[input.readVarInt(true)]);
        data.setDefaultValue(kryo.readClassAndObject(input));
        return data;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    protected FilteredIterableOrderedMap<?, ?, ?> createCopy(Kryo kryo, FilteredIterableOrderedMap<?, ?, ?> original) {
        FilteredIterableOrderedMap data = new FilteredIterableOrderedMap(original.getFilter(), original.getEditor(), original.size(), original.getLoadFactor(), original.getOrderType());
        data.setDefaultValue(original.getDefaultValue());
        return data;
    }
}
