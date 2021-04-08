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
function OCRParser(reader) {
    this.accountReader = reader; // || new AccountFileReader(stream)
    const _this = this;

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
        this.accountReader.parseAccountNumberRow(function (group) {
            retAccountNums.push(_this.parseUnfoldedNumbers(_this.accountReader.unfoldRawRow(group)));
        });
        return retAccountNums;
    };

};

module.exports = OCRParser;