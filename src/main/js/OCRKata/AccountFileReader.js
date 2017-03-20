function AccountFileReader(lineStreamer) {
        this.reader = lineStreamer;
    }

AccountFileReader.prototype = {
    unfoldRawRow: function(rawRow) {
        return [];
    },

    parseGroups: function(groupHandler) {
        let row;
        let group = [];
        let retAccountNums = [];
        while ((row = this.reader()) !== null) {
            // - Group raw lines by number of rows for an account number
            if (row !== "") {
                group.push(row);
            } else {
                groupHandler(group);
            }
        }
    }

};

module.exports = AccountFileReader;