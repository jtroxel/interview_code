/**
 * User: jtroxel
 * Date: 3/16/17
 * Time: 4:22 PM
 *
 */
var td = require('testdouble');
var Jasmine = require('jasmine');
var j = new Jasmine();
var subjectPath = 'src/main/js/OCRKata/';

var OCRParser = require(subjectPath + 'OCRParser.js');
// var AccountFileReader = td.replace(subjectPath + 'AccountFileReader.js');

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

    afterEach(function () {
        td.reset();
    });
    let rawRowsZeros = [
        ' _  _  _  _  _  _  _  _ ',
        '| || || || || || || || |',
        '|_||_||_||_||_||_||_||_|'
    ]

    it("can be constructed", function() {
        expect(new OCRParser()).not.toBe(null);
    });

    // What does a OCR Parser need to "take this file and parse it into actual account numbers"
    // - Read a line of raw input
    // - Group raw lines by number of rows for an account number
    // - Create a list of unfolded of character data [['', '_', '', '|', '', ...], []]
    // - Parse list of unfolded character data into an account number

    // *** Entry point / collaboration test for OCRParser
    describe("parseAccountNumberRow", function () {
        let reader, subject;
        beforeEach(function() {
            reader = new (td.replace(subjectPath + 'AccountFileReader.js'));
            subject = new OCRParser(reader);
        });
        it("coordinates collaborators", function () {
            td.when(reader.parseAccountNumberRow()).thenCallback(rawRowsZeros);
            td.when(reader.unfoldRawRow(rawRowsZeros)).thenReturn(unfoldedZeros());


            expect(subject.parseAccounts()).toEqual(["00000000"]);
        });

    });

    describe("AccountFileReader", function() {
        let subject;
        beforeEach(function() {
            subject = new (require(subjectPath + 'AccountFileReader.js'));
        });

        describe("unfoldRawRow", function () {
            it("unfolds zeros", function () {
                expect(subject.unfoldRawRow(rawRowsZeros)).toEqual(unfoldedZeros())
            });
        });
        describe("parseAccountNumberRow", function () {
        //     it("unfolds zeros", function () {
        //         expect(subject.unfoldRawRow(rawRowsZeros)).toEqual(unfoldedZeros())
        //     });
        });

    });

    function unfoldedZeros() {
        let unfoldedNumber = [];
        for (let i = 0; i < 8; i++) {
            unfoldedNumber.push(" _ | ||_|".split(''))
        }
        return unfoldedNumber;
    }

});

// =============
j.execute();
