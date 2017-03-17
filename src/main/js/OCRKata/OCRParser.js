/**
 * User: jtroxel
 * Date: 3/17/17
 * Time: 9:52 AM
 *
 */

const lineArtNumbers = {
    " _ | ||_|": 0,
    // "     |  |": 0,
    // " _  _||_ ": 0,
};
function OCRParser() {
    return {
        matchUnfolded: function (unfoldedNumbers) {
            return lineArtNumbers[unfoldedNumbers[unfoldedNum].join('')];
        }, parseUnfoldedNumbers: function (unfoldedNumbers) {
            let retStr = "";
            for (unfoldedNum in unfoldedNumbers) {
                retStr += this.matchUnfolded(unfoldedNumbers);
            }
            return retStr;
        }
    }
}

module.exports = OCRParser;