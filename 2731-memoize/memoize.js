/**
 * @param {Function} fn
 * @return {Function}
 */
function memoize(fn) {
    const cache = new Map();

    return function(...args) {
        // Create a unique key for the arguments. JSON.stringify handles various types.
        const key = JSON.stringify(args);
        
        if (cache.has(key)) {
            return cache.get(key);
        }
        
        // Call the original function if the result is not in the cache
        const result = fn(...args);
        cache.set(key, result);
        
        return result;
    };
}

/**
 * Example usage with the 'sum' function:
 * const sum = (a, b) => a + b;
 * const memoizedSum = memoize(sum);
 * memoizedSum(2, 2); // 4
 * memoizedSum(2, 2); // 4 (cached)
 * memoizedSum(1, 2); // 3
 */


/** 
 * let callCount = 0;
 * const memoizedFn = memoize(function (a, b) {
 *	 callCount += 1;
 *   return a + b;
 * })
 * memoizedFn(2, 3) // 5
 * memoizedFn(2, 3) // 5
 * console.log(callCount) // 1 
 */