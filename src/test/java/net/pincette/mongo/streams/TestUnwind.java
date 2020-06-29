package net.pincette.mongo.streams;

import static net.pincette.json.Factory.a;
import static net.pincette.json.Factory.f;
import static net.pincette.json.Factory.o;
import static net.pincette.json.Factory.v;
import static net.pincette.util.Collections.list;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import javax.json.JsonObject;
import org.apache.kafka.streams.test.TestRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestUnwind extends Base {
  @Test
  @DisplayName("$unwind")
  public void unwind() {
    final List<TestRecord<String, JsonObject>> result =
        runTest(a(o(f("$unwind", v("$test")))), list(o(f(ID, v("0")), f("test", a(v(0), v(1))))));

    assertEquals(2, result.size());
    assertEquals(o(f(ID, v("0")), f("test", v(0))), result.get(0).value());
    assertEquals(o(f(ID, v("0")), f("test", v(1))), result.get(1).value());
  }
}
