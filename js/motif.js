var assert = require('./assert.js');

delete String.prototype.indexOf;

if (typeof String.prototype.indexOf === 'undefined') {
    String.prototype.indexOf = function(findStr, fromIdx) {
        if (this.length == 0)
            return -1;

        var findFrom = fromIdx || 0;

        var splits = this.split(findStr, 1);

        if (splits[0] === this.toString()) {
            return -1
        }
        return splits[0].length;

        // while (findFrom <= (this.length-findStr.length)) {
        //     if (this.substring(findFrom, findFrom + findStr.length) === findStr) {
        //         return findFrom;
        //     }
        //     findFrom++;
        // }
        // return -1
    }
}

assert.equal('thisiscool'.indexOf('blah'), -1);
assert.equal('this'.indexOf('th'), 0);
assert.equal('this'.indexOf('is'), 2);
assert.equal('thisiscool'.indexOf('is'), 2);
assert.equal('thisisnotcool'.indexOf('not'), 6);
assert.equal('thisisnotcool'.indexOf('cool'), 9);
assert.equal('thisisnotcool'.indexOf('thisisnotcool'), 0);
