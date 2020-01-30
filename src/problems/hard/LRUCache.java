package problems.hard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Implement an LRU (Least Recently Used) cache. It should be able to be initialized 
 * with a cache size n, and contain the following methods:

set(key, value): sets key to value. If there are already n items in the cache and 
we are adding a new item, then it should also remove the least recently used item.

get(key): gets the value at key. If no such key exists, return null.
Each operation should run in O(1) time.
 * @author Andrew
 *
 */
@Ignore
public class LRUCache<K,V> {
	public static void main(String[] args) {
		LRUCache<String, String> localCache = new LRUCache<>(3);
		localCache.set("Andrew", "Developer");
		localCache.set("Nicole", "Pricing Analyst");
		localCache.set("Karan", "Manager");
		localCache.set("Seth", "Student");
		assertEquals("Pricing Analyst", localCache.get("Nicole"));
		assertNull(localCache.get("Andrew"));
	}
	
	static final Logger log = LogManager.getRootLogger();
	
	static class MetaValue<V> {
		V val;
		Instant lastUsed;
		
		public MetaValue(V val) {
			this(val, Instant.now());
		}
		
		public MetaValue(V val, Instant i) {
			this.val = val;
			this.lastUsed = i;
		}
	}
	
	Map<K,MetaValue<V>> cache;
	
	final int maxSize;
	
	public LRUCache(int n) {
		maxSize = n;
		cache = new HashMap<>(maxSize);
	}
	
	public void set(K key, V val) {
		MetaValue<V> metaVal = new MetaValue<>(val);
		metaVal.lastUsed = Instant.now();
		if (cache.size() < maxSize) {
			cache.put(key, metaVal);
		} else if (cache.size() == maxSize) {
			log.info(String.format("Cache max size of %d reached", maxSize));
			Optional<Map.Entry<K, MetaValue<V>>> optMap = cache.entrySet().stream()
			  .min((e1, e2) -> e1.getValue().lastUsed.isBefore(e2.getValue().lastUsed) ? 1 : -1);
			if (optMap.isPresent()) {
				cache.remove(optMap.get().getKey());
			}
			cache.put(key, metaVal);
		}
	}
	
	public V get(K key) {
		MetaValue<V> val = cache.get(key);
		if (val != null) {
			val.lastUsed = Instant.now();
			cache.put(key, val);
			return val.val;
		}
		return null;
	}
	
	@Test
	public void defaultTest() {
		LRUCache<String, String> localCache = new LRUCache<>(3);
		localCache.set("Andrew", "Developer");
		localCache.set("Nicole", "Pricing Analyst");
		localCache.set("Karan", "Manager");
		localCache.set("Seth", "Student");
		assertEquals("Pricing Analyst", localCache.get("Nicole"));
		assertNull(localCache.get("Andrew"));
	}
	
}
