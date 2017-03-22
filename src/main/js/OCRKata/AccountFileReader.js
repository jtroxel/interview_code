function AccountFileReader(lineStreamer) {
        this.lineReader = lineStreamer;
    }

AccountFileReader.prototype = {
    unfoldRawRow: function(rawRow) {
        let retRow = [];

        // TODO: javascript array "stream" methods: [1..8].forEach(f -> {})...
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
        // TODO:  functional this.lineReader.eachRow do
        while ((line = this.lineReader()) !== null) {
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