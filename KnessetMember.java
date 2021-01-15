/**
 * Created by Shimmy on 3/12/2017.
 */
public class KnessetMember {
    static double knessetMembersEnthusiasmThreshold = 5;
    String firstName, lastName, title;
    double mkSocialTendency, mkEconomyTendency, mkPoliticalTendency;
    int surveyThreshold;

    /**
     * Creates a new KnessetMember with the given characteristics.
     * @param ​knessetMember​F​irstName ​The first name of the KnessetMember.
     * @param ​knessetMemberL​astName ​The last name of the KnessetMember.
     * @param ​socialTendency ​The weight the KnessetMember assigns to the social aspects of laws.
     * @param ​economyTendency ​The weight the KnessetMember assigns to the economy aspects of laws.
     * @param ​politicalTendency ​The weight the KnessetMember assigns to the political aspects of laws.
     * @param ​knessetMember​SurveyThreshold ​The minimal public support a law must have for this KnessetMember
    to join it.
     */
    KnessetMember(String knessetMemberFirstName, String knessetMemberLastName,double socialTendency, double
            economyTendency, double politicalTendency, int knessetMemberSurveyThreshold) {
        firstName = knessetMemberFirstName;
        lastName = knessetMemberLastName;
        title = "Knesset Member";
        mkSocialTendency = socialTendency;
        mkEconomyTendency = economyTendency;
        mkPoliticalTendency = politicalTendency;
        surveyThreshold = knessetMemberSurveyThreshold;
    }

    /**
     * Returns a string representation of the KnessetMember, which is a sequence of its first name, last name and title
     * separated by a single white space. For example, if the KnessetMember's first name is "Yehudah" and his last name
     * is "Glick", this method will return the String "Knesset Member Yehudah Glick".
     * @return ​the String representation of this KnessetMember.
     */
    String stringRepresentation(){
        return title+" "+firstName+" "+lastName;
    }
    /**
     * Returns the interest value this KnessetMember assigns to the given Law.
     * @param ​law ​The law to assess.
     * @param ​surveyResult ​The result of a survey made for this law
     * @return ​the interest value this KnessetMember assigns to the given law. 0 if survey result does not pass threshold.
     */
    double getLawScore(Law law, int surveyResult){
        if (surveyResult < surveyThreshold){
            return 0;
        } else {
            return law.econVal*mkEconomyTendency+law.politicalVal*mkPoliticalTendency+law.socialVal*mkSocialTendency;
        }
    }
    /**
     * Returns true of this KnessetMember will join the given law (law score is bigger than
     knessetMembersEnthusiasmThreshold), false otherwise.
     * @param ​law ​The law to asses.
     * @return ​true of this KnessetMember will join the given law, false otherwise.
     */
    boolean willJoinLaw(Law law, int surveyResult){
        if (getLawScore(law,surveyResult) > knessetMembersEnthusiasmThreshold) {
            return true;
        } else {
            return false;
        }
    }
}
