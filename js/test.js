var assert = require('/Users/jtroxel/dev/appdyn/src/assert.js')

var fib = function (n) {
  var fn = arguments.callee;
  if (!fn.prototype.cache) {

    if (n < 2) {
      return n
    }
    this.prototype[cache] = fib(n - 1) + fib(n - 2);
  }
  return fn.prototype.cache;
}

assert.equal(fib(0), 0);
assert.equal(fib(1), 1);
assert.equal(fib(2), 1);
assert.equal(fib(3), 2);
assert.equal(fib(6), 8);

//assert.equal(true, false);
//console.log('foo');