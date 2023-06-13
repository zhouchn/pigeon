package io.pigeon.access;

import io.protostuff.Tag;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/27
 **/
public class User {
    @Tag(1)
    private Long id;
    @Tag(2)
    private String name;
    @Tag(3)
    private int age;
    @Tag(4)
    private int[] nums;
    @Tag(5)
    private Integer[] integers;
    @Tag(6)
    private List<Integer> intList;
    @Tag(7)
    private Long birthday;
    @Tag(8)
    private Set<String> hobbits;
    @Tag(9)
    private Map<String, String> attrs;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }

    public int[] getNums() {
        return nums;
    }

    public User setNums(int[] nums) {
        this.nums = nums;
        return this;
    }

    public Long getBirthday() {
        return birthday;
    }

    public User setBirthday(Long birthday) {
        this.birthday = birthday;
        return this;
    }

    public Set<String> getHobbits() {
        return hobbits;
//        return Collections.emptySet();
    }

    public User setHobbits(Set<String> hobbits) {
        this.hobbits = hobbits;
        return this;
    }

    public Map<String, String> getAttrs() {
        return attrs;
    }

    public User setAttrs(Map<String, String> attrs) {
        this.attrs = attrs;
        return this;
    }
}