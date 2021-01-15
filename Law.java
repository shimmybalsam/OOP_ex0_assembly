
/**
 * This class represents a law.
 */
public class Law {


    String title, partyOfInitiator;
    int publicationYear, socialVal, econVal, politicalVal, lawSupporters = 1;
    KnessetMember initiator;


    /**
     * Creates a new law with the given characteristic.
     * @param lawTitle The title of the law.
     * @param lawInitiator The initiator of the law.
     * @param lawParty The party of the lawInitiator
     * @param lawYearOfPublication The year the book was published.
     * @param lawSocialValue The comic value of the book.
     * @param lawEconomyValue The dramatic value of the book.
     * @param lawPoliticalValue The educational value of the book.
     */
    Law(String lawTitle, KnessetMember lawInitiator, String lawParty, int lawYearOfPublication, int lawSocialValue, int lawEconomyValue,
        int lawPoliticalValue){
        title = lawTitle;
        initiator = lawInitiator;
        partyOfInitiator = lawParty;
        publicationYear = lawYearOfPublication;
        socialVal = lawSocialValue;
        econVal = lawEconomyValue;
        politicalVal = lawPoliticalValue;
    }

   /*----=  Instance Methods  =-----*/

    /**
     * Returns a string representation of the law, which is a sequence
     * of the title, intiator name and year of publication, separated by
     * commas, inclosed in square brackets. For example, if the law is
     * titled "Fix 128 to Bituah Leumi order", was intiated by Eli Alaluf and published in 2016,
     * this method will return the string:
     * "[Fix 128 to Bituah Leumi order,Eli Alaluf,Kulanu,2016]"
     * @return the String representation of this law.
     */
    String stringRepresentation(){
        return "["+title+","+initiator.stringRepresentation()+","+ partyOfInitiator +","+publicationYear+"]";
    }

    /**
     * Adds another MK to support this law
     */
    void addJoinedKnessetMember(){

        lawSupporters++;
    }

    /**
     * remove a single MK support. If only 1 MK supports the law, do nothing.
     */
    void subJoinedKnessetMember(){
        if(lawSupporters > 1) {
            lawSupporters--;
        }

    }

    /**
     * Returns the number of MKs that are currently supporting this Law (including initiator)
     * @return number of Knesset Members that are currently support this law
     */
    int getCurrentNumberOfKnessetMembers(){

        return lawSupporters;
    }


}