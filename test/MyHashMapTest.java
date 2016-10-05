import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class MyHashMapTest {

    private MyHashMap<Integer,String> hashMap = new MyHashMap<>();

    @Test
    public void testSize() throws Exception {
        Assert.assertThat(0, is(hashMap.size()));
        hashMap.put(1, "Nastya");
        Assert.assertThat(1, is(hashMap.size()));
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(hashMap.isEmpty());
        hashMap.put(1, "Nastya");
        Assert.assertFalse(hashMap.isEmpty());
    }

    @Test
    public void testContainsKey() throws Exception {
        hashMap.put(1, "Nastya");
        hashMap.put(null, "Nastya");

        Assert.assertTrue(hashMap.containsKey(1));
        Assert.assertTrue(hashMap.containsKey(null));
    }

    @Test
    public void testContainsValue() throws Exception {
        hashMap.put(1, "Nastya");
        hashMap.put(null, "Katya");
        hashMap.put(2, "Katya");
        hashMap.put(null, null);

        Assert.assertTrue(hashMap.containsValue("Nastya"));
        Assert.assertTrue(hashMap.containsValue("Katya"));
        Assert.assertTrue(hashMap.containsValue(null));
    }

    @Test
    public void testPut() throws Exception {
        hashMap.put(null, "Nastya");
        hashMap.put(2, "Katya");

       Assert.assertThat("Nastya", is(hashMap.get(null)));
       Assert.assertThat("Katya", is(hashMap.get(2)));
    }

    @Test
    public void testGet() throws Exception {
        hashMap.put(1, "Nastya");
        hashMap.put(2, "Katya");
        hashMap.put(null, null);

        Assert.assertThat(null, is(hashMap.get(null)));
        Assert.assertThat("Katya", is(hashMap.get(2)));

        hashMap.put(1, "Petya");
        Assert.assertThat("Petya", is(hashMap.get(1)));
        Assert.assertThat(3, is(hashMap.size()));

        hashMap.put(null, "Olya");
        Assert.assertThat("Olya", is(hashMap.get(null)));
        Assert.assertThat(3, is(hashMap.size()));
    }

    @Test
    public void testRemove() throws Exception {
        hashMap.put(1, "Nastya");
        Assert.assertThat("Nastya", is(hashMap.remove(1)));
        Assert.assertThat(0, is(hashMap.size()));
    }

    @Test
    public void testClear() throws Exception {
        hashMap.put(2, "Katya");
        hashMap.clear();
        Assert.assertThat(0, is(hashMap.size()));
    }
}
