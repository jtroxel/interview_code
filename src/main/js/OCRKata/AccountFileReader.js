function AccountFileReader(lineStreamer) {
        this.reader = lineStreamer;
    }

AccountFileReader.prototype = {
    unfoldRawRow: function(rawRow) {
        let retRow = [];
        for(let digit = 0; digit < 8; digit++) {
            retRow[digit] = [];
            for (let digitRow = 0; digitRow < 3; digitRow++) {
                let start = digit * 3;
                retRow[digit].push(...rawRow[digitRow].substring(start, start+3).split(''))
            }
        }
        return retRow;
    },

    parseAccountNumberRow: function(rowHandler) {
        let line;
        let accountNumberRow = [];
        let retAccountNums = [];
        while ((line = this.reader()) !== null) {
            // - Group raw lines by number of rows for an account number
            if (line !== "") {
                accountNumberRow.push(line);
            } else {
                rowHandler(accountNumberRow);
            }
        }
    }

};

module.exports = AccountFileReader;