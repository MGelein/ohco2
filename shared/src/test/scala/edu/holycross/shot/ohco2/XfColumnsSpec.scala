package edu.holycross.shot.ohco2
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import java.io._


class XfColumnsSpec extends FlatSpec {


    val scholiaDelimited = """urn:cts:greekLit:tlg5026.msA.hmt:1.1.lemma#<div xmlns="http://www.tei-c.org/ns/1.0" n="lemma"> <p> μῆνιν ἄειδε</p></div>
urn:cts:greekLit:tlg5026.msA.hmt:1.1.comment#<div xmlns="http://www.tei-c.org/ns/1.0" n="comment"> <p> ζητοῦσι δια τί ἀπὸ τῆς μήνιδος ἤρξατο οὕτως δυσφήμου ὀνόματος· δια δύο ταῦτα· πρῶτον μὲν ἵν' ἐκ τοῦ πάθους ἀπκαταῥρεύσῃ τὸ τοιοῦτο μόριον τῆς ψυχῆς καὶ προσεκτικωτέρους τοὺς ἀκροατὰς ἐπι τοῦ μεγέθους ποιήσῃ καὶ προσεθίζῃ φέρειν γενναίως ἡμᾶς τὰ πάθη. μέλλων πολλέμους ἀπαγγέλλειν· δεύτερον. ἵνα τὰ ἐγκώμια τῶν Ἑλλήνων πιθανώτερα ποιήσῃ· ἐπεὶ δὲ ἔμελλε, νικωντας ἀποφαίνειν τοὺς Ἑλληνας, εἰκότως οὐ κατατρέχει ἀξιοπιστότερον ἐκ τοῦ μὴ παντα χαρίζεσθαι τῷ εκείνων ἐπαίνῳ· ἤρξατο μὲν ἀπὸ μήνιδος ἐπείπερ αὕτη τοῖς πρακτικοῖς ὑπόθεσις γέγονεν· ἄλλῳς τε καὶ τραγῳδίαις τραγικὸν ἐξεῦρε προοίμιον· καὶ γὰρ προσεκτικοὺς ἡμᾶς ἡ τῶν ἀτυχημάτων διήγησις ἐργάζεται· καὶ ὡς ἄριστος ϊατρὸς, πρῶτον ἀναστέλλων τὰ νοσήματα τῆς ψυχῆς ὕστερον τὴν ΐασιν ἐπάγει. <placeName n="urn:cite:hmt:place.place3"> Ἑλληνικὸν</placeName> δὲ τὸ προ τέλει τὰς ηδονὰς ἐπάγειν· ῾ϊστέον δέ, ὥσπερ ἐπι συκῆς πρωτον μέν ἐστιν όλυνθος εἶτα φίλιξ σύκον ϊσχάς. οὕτω πρῶτον· <rs type="waw"> ὀργή</rs> · <rs type="waw"> θυμός</rs> · <rs type="waw"> χόλος</rs> · <rs type="waw"> κότος</rs> · <rs type="waw"> μῆνις</rs> · ὅμως ὁ ποιητὴς ὡς συνωνύμοις ὀνόμασιν ἐπὶ <persName n="urn:cite:hmt:pers.pers1"> Ἀχιλλεως</persName> χρῆται <cit> <q> ἢἐ <seg type="word"> χόλ <unclear> ο</unclear> ν</seg> παύσειεν, ἐρητύσειε τε θυμόν</q> <ref type="urn"> urn:cts:greekLit:tlg0012.tlg001:1.192</ref></cit> · <cit> <q> οὐδ' ὄθομαι κοτέοντος</q> <ref type="urn"> urn:cts:greekLit:tlg0012.tlg001:1.181</ref></cit> , <cit> <q> αὐτὰρ ὁ μήνιε νηυσίν</q> <ref type="urn"> urn:cts:greekLit:tlg0012.tlg001:1.488</ref></cit> ·</p></div>
urn:cts:greekLit:tlg5026.msA.hmt:1.2.lemma#<div xmlns="http://www.tei-c.org/ns/1.0" n="lemma"> <p> μῆνις</p></div>
urn:cts:greekLit:tlg5026.msA.hmt:1.2.comment#<div xmlns="http://www.tei-c.org/ns/1.0" n="comment"> <p> παρὰ τὸ <rs type="waw"> μένω</rs> <rs type="waw"> μῆνις</rs> ὡς <rs type="waw"> ἐνὸς</rs> <rs type="waw"> ἦνις</rs> · οἱ δὲ περὶ <persName n="urn:cite:hmt:pers.pers3"> Γλαύκωνα</persName> τὸν <placeName n="urn:cite:hmt:place.place47"> <seg type="word"> Ταρ <unclear> σέα</unclear></seg></placeName> ἠξίουν ὀξύνειν τὸ ὄνομα οὐκ ὀρθῶς.</p></div>
urn:cts:greekLit:tlg5026.msA.hmt:1.3.lemma#<div xmlns="http://www.tei-c.org/ns/1.0" n="lemma"> <p> ἄειδε</p></div>
urn:cts:greekLit:tlg5026.msA.hmt:1.3.comment#<div xmlns="http://www.tei-c.org/ns/1.0" n="comment"> <p> ὅτι κατὰ τὴν ποιητικὴν ἤτοι άδειαν ἠ συνήθειαν λαμβάνει τὰ προστακτικὰ ἀντι εὐκτικῶν· καὶ γὰρ <persName n="urn:cite:hmt:pers.pers4"> Ἡσίοδός</persName> φησι· <q> δεῦτε δὴ ἐννέπετε <note resp="Erbse"> opp.2</note></q> , καὶ <persName n="urn:cite:hmt:pers.pers5"> Πίνδαρος</persName> · <q> μαντεύο <persName n="urn:cite:hmt:pers.pers6"> Μοῦσα</persName> <note resp="Erbse"> fr. 150 sn. </note></q> , καὶ <persName n="urn:cite:hmt:pers.pers7"> Ἀντίμαχος</persName> ὁ <placeName n="urn:cite:hmt:place.place54"> <seg type="word"> Κολοφ <unclear> ώνιος.</unclear></seg></placeName> <q> ἐννέπετε <persName n="urn:cite:hmt:pers.pers8"> <seg type="word"> Κρονί <unclear> ανος</unclear></seg> Διὸς </persName> μεγάλοιο θύγατρες <note resp="Erbse"> fr. 1 w</note></q> · δευτερον δὲ ὅτι οὐ κατὰ ἀλήθειαν ταῖς <persName n="urn:cite:hmt:pers.pers6"> Μούσαις</persName> ἐπιτάσσουσιν, ἀλλ' ἑαυτοῖς⁑</p></div>
urn:cts:greekLit:tlg5026.msA.hmt:1.4.lemma#<div xmlns="http://www.tei-c.org/ns/1.0" n="lemma"> <p> θεά</p></div>
urn:cts:greekLit:tlg5026.msA.hmt:1.4.comment#<div xmlns="http://www.tei-c.org/ns/1.0" n="comment"> <p> οὕτως εἴωθε τὴν <persName n="urn:cite:hmt:pers.pers6"> Μοῦσαν</persName> καλεῖν· ἀμέλει καὶ ἐν <title> Ὀδυσσεία</title> · <cit> <q> ἄνδρα μοι ἔννεπε <persName n="urn:cite:hmt:pers.pers6"> Μοῦσα</persName> <ref type="urn"> urn:cts:greekLit:tlg0012.tlg002:1.1</ref></q></cit> ⁑</p></div>
urn:cts:greekLit:tlg5026.msA.hmt:1.5.lemma#<div xmlns="http://www.tei-c.org/ns/1.0" n="lemma"> <p> <persName n="urn:cite:hmt:pers.pers1"> Πηληϊάδεω</persName> <persName n="urn:cite:hmt:pers.pers1"> Ἀχιλῆος</persName></p></div>
urn:cts:greekLit:tlg5026.msA.hmt:1.5.comment#<div xmlns="http://www.tei-c.org/ns/1.0" n="comment"> <p> οὕτως ἀναγνωστεον δια τοῦ ενὸς <rs type="waw"> λ</rs> καὶ διὰ τὸ μέτρον καὶ διὰ τὸ ἄχος ὅ ἐστι λύπην ἐπενεγκεῖν τοῖς Ἰλιεῦσιν, οἱ δὲ παρὰ τὸ μὴ θιγεῖν χείλεσι θηλῆς ὅλως γὰρ οὐ μετέσχε γάλακτος⁑</p></div>
"""
  val corpus = Corpus(scholiaDelimited,"#")

  // urn: String, nxt: String, prv: String, txt: String
  "A vector of XfColumns objects"  should "be automatically generated from a corpus of citable texts" in {
    val xfcols = corpus.to82xfVector
    xfcols match {
      case xf: Vector[XfColumns] => assert(true)
      case _ => fail("Created wrong kind of object")
    }
  }
  it should "have an empty string for the previous value of the first element" in {
    val xfcols = corpus.to82xfVector
    assert(xfcols(0).prv.isEmpty)
  }
  it should "have a nonempty string for all other values of the first element" in {
    val xfcols = corpus.to82xfVector
    assert(xfcols(0).urn.nonEmpty)
    assert(xfcols(0).nxt.nonEmpty)
    assert(xfcols(0).txt.nonEmpty)
  }
  it should "have an empty string for the next value of the last element" in {
    val xfcols = corpus.to82xfVector
    assert(xfcols.last.nxt.isEmpty)
  }

  it should "have a non-empty string for all other values of the last element" in {
    val xfcols = corpus.to82xfVector
    assert(xfcols.last.urn.nonEmpty)
    assert(xfcols.last.prv.nonEmpty)
    assert(xfcols.last.txt.nonEmpty)
  }

  it should "have a non-empty string for all other values " in {
    val xfcols = corpus.to82xfVector
    for (c <- xfcols) {
      assert(c.urn.nonEmpty)
      assert(c.txt.nonEmpty)
    }
    val middleRows = xfcols.drop(1).dropRight(1)
    assert (middleRows.size == xfcols.size - 2)
    var count = 0
    for (c <- middleRows) {
      assert(c.prv.nonEmpty)
      assert(c.nxt.nonEmpty)
    }
  }
  it should "have a function to format as a single string" in {
    val xfcols = corpus.to82xfVector
    assert(xfcols.head.rowString("#") == """urn:cts:greekLit:tlg5026.msA.hmt:1.1.lemma#urn:cts:greekLit:tlg5026.msA.hmt:1.1.comment##<div xmlns="http://www.tei-c.org/ns/1.0" n="lemma"> <p> μῆνιν ἄειδε</p></div>""")
  }

}