function AccountFileReader(lineStreamer) {
        this.reader = lineStreamer;
    }

AccountFileReader.prototype = {
    unfoldRawRow: function(rawRow) {
        return [];
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