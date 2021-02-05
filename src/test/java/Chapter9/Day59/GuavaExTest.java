package Chapter9.Day59;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GuavaExTest {

    @DisplayName("구아바 맵 생성 테스트")
    @Test
    public void makeMap() {
        // JDK
        Map<Integer, String> map1 = new HashMap<>();
        // GUAVA
        Map<Integer, String> guavaMap1 = Maps.newHashMap();
        Map<Integer, String> guavaMap2 = Maps.newHashMap(map1);
        Map<Integer, String> guavaMap3 = Maps.newHashMapWithExpectedSize(10);

        assertThat(guavaMap3.size()).isEqualTo(10);
    }
}