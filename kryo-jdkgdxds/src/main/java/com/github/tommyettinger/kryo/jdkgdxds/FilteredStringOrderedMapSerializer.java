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
import com.github.tommyettinger.ds.FilteredStringOrderedMap;
import com.github.tommyettinger.ds.Utilities;

import java.util.NoSuchElementException;

public class FilteredStringOrderedMapSerializer extends MapSerializer<FilteredStringOrderedMap<?>> {
    public FilteredStringOrderedMapSerializer() {
        super();
        setKeysCanBeNull(false);
    }

    @Override
    protected void writeHeader(Kryo kryo, Output output, FilteredStringOrderedMap<?> map) {
        CharFilter fil = map.getFilter();
        if(fil == null)
            throw new NoSuchElementException("A FilteredStringOrderedMap must have a non-null filter to be serialized.");
        output.writeString(fil.getName());
    }

    @Override
    protected FilteredStringOrderedMap<?> create(Kryo kryo, Input input, Class<? extends FilteredStringOrderedMap<?>> type, int size) {
        return new FilteredStringOrderedMap<>(CharFilter.get(input.readString()), size, Utilities.getDefaultLoadFactor());
    }

    @Override
    protected FilteredStringOrderedMap<?> createCopy(Kryo kryo, FilteredStringOrderedMap<?> original) {
        return new FilteredStringOrderedMap<>(original.getFilter(), original.size(), original.getLoadFactor());
    }
}
