/**
 * User: jtroxel
 * Date: 3/17/17
 * Time: 9:52 AM
 *
 */

const lineArtNumbers = {
    " _ | ||_|": 0,
    // TODO other digits
    // "     |  |": 0,
    // " _  _||_ ": 0,
};
function OCRParser(read) {

    return {
        lineReader: read,
        // TODO:  make these injectable?
        // unfolder: this.getUnfoldedNumsFromRawRow,
        // parser: this.parseUnfoldedNumbers,

        matchUnfolded: function (unfoldedNumbers) {
            return lineArtNumbers[unfoldedNumbers[unfoldedNum].join('')];
        },
        getUnfoldedNumsFromRawRow: function (rawRow) {
            return [];
        },
        parseFile: function() {
            let row;
            let group = [];
            let retAccountNums = [];
            while ((row = this.lineReader()) !== null) {
                // - Group raw lines by number of rows for an account number
                if (row !== "") {
                    group.push(row);
                } else {
                    retAccountNums.push(this.parseUnfoldedNumbers(this.getUnfoldedNumsFromRawRow(group)));
                    group = [];
                }
            }
            return retAccountNums;
        },
        parseUnfoldedNumbers: function (unfoldedNumbers) {
            let retStr = "";
            for (unfoldedNum in unfoldedNumbers) {
                retStr += this.matchUnfolded(unfoldedNumbers);
            }
            return retStr;
        }
    }
}

module.exports = OCRParser;