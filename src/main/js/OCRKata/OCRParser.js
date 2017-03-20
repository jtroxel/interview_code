/**
 * User: jtroxel
 * Date: 3/17/17
 * Time: 9:52 AM
 *
 */
var AccountFileReader = require('./AccountFileReader');
const lineArtNumbers = {
    " _ | ||_|": 0,
    // TODO other digits
    // "     |  |": 0,
    // " _  _||_ ": 0,
};
function OCRParser(reader, unfolder) {
    this.accountReader = reader; // e.g. new AccountFileReader(stream)
    this.unfolder = unfolder || this.getUnfoldedNumsFromRawRow;
    const _this = this;

    this.getUnfoldedNumsFromRawRow = function (rawRow) {
        return [];
    };
    this.matchUnfolded = function (unfoldedArr) {
        return lineArtNumbers[unfoldedArr.join('')];
    };

    this.parseUnfoldedNumbers = function (unfoldedNumbers) {
        let retStr = "";
        for (var unfoldedNum in unfoldedNumbers) {
            retStr += this.matchUnfolded(unfoldedNumbers[unfoldedNum]);
        }
        return retStr;
    };

    this.parseAccounts = function () {
        let row;
        let group = [];
        let retAccountNums = [];
        this.accountReader.parseGroups(function (group) {
            retAccountNums.push(_this.parseUnfoldedNumbers(_this.unfolder(group)));
        });
        return retAccountNums;
    };

};

module.exports = OCRParser;