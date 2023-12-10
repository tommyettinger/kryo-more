/*
 * Copyright (c) 2023 See AUTHORS file.
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
import com.github.tommyettinger.ds.CharFilter;
import com.github.tommyettinger.ds.FilteredIterableMap;
import com.github.tommyettinger.ds.FilteredIterableMap;
import com.github.tommyettinger.ds.Utilities;
import com.github.tommyettinger.function.ObjPredicate;
import com.github.tommyettinger.function.ObjToSameFunction;

import java.util.NoSuchElementException;

public class FilteredIterableMapSerializer extends MapSerializer<FilteredIterableMap<?, ?, ?>> {
    public FilteredIterableMapSerializer() {
        super();
        setKeysCanBeNull(false);
    }

    @Override
    protected void writeHeader(Kryo kryo, Output output, FilteredIterableMap<?, ?, ?> collection) {
        ObjPredicate<?> filter = collection.getFilter();
        ObjToSameFunction<?> editor = collection.getEditor();
        if(filter == null || editor == null)
            throw new NoSuchElementException("A FilteredIterableMap must have a non-null filter and editor to be serialized.");
        if(kryo.getClassResolver().getRegistration(filter.getClass()) == null)
            kryo.register(filter.getClass());
        if(kryo.getClassResolver().getRegistration(editor.getClass()) == null)
            kryo.register(editor.getClass());
        kryo.writeClassAndObject(output, filter);
        kryo.writeClassAndObject(output, editor);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    protected FilteredIterableMap<?, ?, ?> create(Kryo kryo, Input input, Class<? extends FilteredIterableMap<?, ?, ?>> type, int size) {
        return new FilteredIterableMap((ObjPredicate<?>)kryo.readClassAndObject(input), (ObjToSameFunction<?>) kryo.readClassAndObject(input), size, Utilities.getDefaultLoadFactor());
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    protected FilteredIterableMap<?, ?, ?> createCopy(Kryo kryo, FilteredIterableMap<?, ?, ?> original) {
        return new FilteredIterableMap(original.getFilter(), original.getEditor(), original.size(), original.getLoadFactor());
    }
}
