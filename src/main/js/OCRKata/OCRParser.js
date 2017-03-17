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
        parseUnfoldedNumbers: function (unfoldedNumbers) {
            let retStr = "";
            for (unfoldedNum in unfoldedNumbers) {
                retStr += lineArtNumbers[unfoldedNumbers[unfoldedNum].join('')];
            }
            return retStr;
        }
    }
}

module.exports = OCRParser;