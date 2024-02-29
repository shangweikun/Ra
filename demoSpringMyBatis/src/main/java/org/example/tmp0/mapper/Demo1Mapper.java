package org.example.tmp0.mapper;

import org.apache.ibatis.annotations.Select;

public interface Demo1Mapper {

    @Select("select count(1) from test_table")
    int getCount();
}
