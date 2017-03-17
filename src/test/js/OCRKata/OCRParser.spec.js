/**
 * User: jtroxel
 * Date: 3/16/17
 * Time: 4:22 PM
 *
 */
var td = require('testdouble');
var Jasmine = require('jasmine');
var j = new Jasmine();

var OCRParser = require('/Users/jtroxel/dev/codecraft/interview_code/src/main/js/OCRKata/OCRParser.js');
// =============
describe("Lib smoke test", function () {
    describe("Jasmine", function () {
        let a;
        it("is working", function () {
            a = true;
            expect(a).toBe(true);
        });
    });
    describe("testdouble.js", function () {
        it("is working", function () {
            let quack = td.function('quack');
            td.when(quack()).thenReturn('not quack');
            expect(quack()).toBe('not quack');
        });
    });
});

describe(OCRParser, function () {
    it("exists", function() {
        expect(new OCRParser()).not.toBe(null);
    })
});

// =============
j.execute();
