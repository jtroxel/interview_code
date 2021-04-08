from typing import List


class Deduper:

    def dedupe(self, dupList: List):
        return list(set(dupList))

    def dedupeListComp(self, dupList: List):
        res = []
        [res.append(x) for x in dupList if x not in res]
        return res
