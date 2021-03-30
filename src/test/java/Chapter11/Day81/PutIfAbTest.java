package Chapter11.Day81;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.*;

class PutIfAbTest {

    private Map<String, String> map;

    @BeforeEach
    void setUp() {
        map = new ConcurrentHashMap<>();
    }

    @DisplayName("putIfAbsent() 는 주어진 키에 매핑된 값이 없으면 새 값을 집어넣고 null 을 반환한다.")
    @Test
    void putIfAbsentTest() {
        String result = map.putIfAbsent("hi", "hello");

        assertThat(result).isNull();
        assertThat(map).containsEntry("hi", "hello");
    }

    @DisplayName("putIfAbsent() 는 주어진 키에 매핑된 값이 있으면 변경하지 않고 원래값을 반환한다.")
    @Test
    void putIfAbsentTest2() {
        map.put("hi", "hello");
        String result = map.putIfAbsent("hi", "bye");

        assertThat(result).isEqualTo("hello");
        assertThat(map).containsEntry("hi", "hello");
    }

    @DisplayName("computeIfAbsent() 는 value 값으로 함수 객체를 줄 수 있다.")
    @Test
    void computeIfAbsentTest() {
        String result = map.computeIfAbsent("hi", key -> key + ("(mingeor)"));

        assertThat(result).isEqualTo("hi(mingeor)");
        assertThat(map).containsEntry("hi", "hi(mingeor)");
    }

    @DisplayName("getOrDefault() 는 키에 맞는 값을 가져오고 없으면 지정한 디폴트 값을 반환한다. 삽입하지는 않는다.")
    @Test
    void get() {
        map.put("hello", "greeting");

        String result = map.getOrDefault("hi", "default String");
        String result2 = map.getOrDefault("hello", "default String");

        assertThat(result).isEqualTo("default String");
        assertThat(result2).isEqualTo("greeting");
    }

    @Test
    void test() {
        String name = "minger";

        String newName = new String("minger");
        String intern = newName.intern();

        assertThat(name).isNotSameAs(newName);
        assertThat(name).isSameAs(intern);
    }
}