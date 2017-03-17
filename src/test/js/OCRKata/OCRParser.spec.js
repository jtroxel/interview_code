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
    it("can be constructed", function() {
        expect(new OCRParser()).not.toBe(null);
    })

    // What does a OCR Parser need to "take this file and parse it into actual account numbers"
    // - Read a line of raw input
    // - Group raw lines by number of rows for an account number
    // - Create a list of unfolded of character data [['', '_', '', '|', '', ...], []]
    // - Parse list of unfolded character data into an account number

    describe("parseUnfoldedNumbers", function() {
        it("can parse 00000000", function () {
            let subject = new OCRParser();
            let unfoldedNumber = [
                [' ', '_', ' ', '|', ' ', '|', '|', '_', '|'],
                [' ', '_', ' ', '|', ' ', '|', '|', '_', '|'],
                [' ', '_', ' ', '|', ' ', '|', '|', '_', '|'],
                [' ', '_', ' ', '|', ' ', '|', '|', '_', '|'],
                [' ', '_', ' ', '|', ' ', '|', '|', '_', '|'],
                [' ', '_', ' ', '|', ' ', '|', '|', '_', '|'],
                [' ', '_', ' ', '|', ' ', '|', '|', '_', '|'],
                [' ', '_', ' ', '|', ' ', '|', '|', '_', '|'],
            ];
            expect(subject.parseUnfoldedNumbers(unfoldedNumber)).toBe("00000000");
        });

    });

});

// =============
j.execute();
