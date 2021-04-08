import unittest

from dedupe_list.dedupe import Deduper

NO_DUPE_RAINBOW = "red,orange,yellow,blue,green,purple"
LONG_RAINBOW = "red,orange,yellow,blue,green,indigo,violet"


class MyTestCase(unittest.TestCase):

    def setUp(self):
        self.subject = Deduper()


    def test_no_dupes(self):
        test_list = list(NO_DUPE_RAINBOW.split(','))
        result = self.subject.dedupe(test_list)
        self.assertTrue(result, list(test_list))
        result = self.subject.dedupeListComp(test_list)
        self.assertTrue(result, list(test_list))

    def test_dupes(self):
        test_list = list(NO_DUPE_RAINBOW.split(',')) + list(LONG_RAINBOW.split(','))
        expected = list("red,orange,yellow,blue,green,purple,indigo,violet".split(','))
        result = self.subject.dedupe(test_list)
        self.assertTrue(result, list(test_list))
        result = self.subject.dedupeListComp(test_list)
        self.assertTrue(result, list(test_list))

if __name__ == '__main__':
    unittest.main()
