package utilities

data class Passport(
    val birthYear: Int,
    val issueYear: Int,
    val expirationYear: Int,
    val height: Int,
    val heightType: String,
    val hairColour: String,
    val eyeColour: String,
    val pid: String,
    val cid: String?
)

data class RawPassport(
    val birthYear: String?,
    val issueYear: String?,
    val expirationYear: String?,
    val height: String?,
    val hairColour: String?,
    val eyeColour: String?,
    val pid: String?,
    val cid: String?
) {
    fun hasRequiredFields() = birthYear.isNotNull() &&
            issueYear.isNotNull() &&
            expirationYear.isNotNull() &&
            height.isNotNull() &&
            hairColour.isNotNull() &&
            eyeColour.isNotNull() &&
            pid.isNotNull()

    private fun fieldsAreValid(): Boolean {
        return isValidBirthYear() &&
                isValidIssueYear() &&
                isValidExpireYear() &&
                isValidEyeColour() &&
                isValidHairColour() &&
                isValidPassportId() &&
                isValidHeight()
    }

    fun isValidPassport() = hasRequiredFields() && fieldsAreValid()

    fun toPassport() =
        Passport(
            birthYear = birthYear!!.toInt(),
            issueYear = issueYear!!.toInt(),
            expirationYear = expirationYear!!.toInt(),
            height = getHeightValue(),
            heightType = getHeightType(),
            hairColour = hairColour!!,
            eyeColour =  eyeColour!!,
            pid = pid!!,
            cid = cid
        )

    private fun getHeightType() = height!!.takeLast(2)
    private fun getHeightValue() = height?.take(height.length - 2).toIntOrDefault()

    private fun isValidBirthYear() = birthYear.toIntOrDefault() in 1920..2002
    private fun isValidIssueYear() = issueYear.toIntOrDefault() in 2010..2020
    private fun isValidExpireYear() = expirationYear.toIntOrDefault() in 2020..2030
    private fun isValidHairColour() = Regex("#[0-9, a-f]{6}").matches(hairColour.toString())
    private fun isValidEyeColour() = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(eyeColour)
    private fun isValidPassportId() = Regex("[0-9]{9}").matches(pid.toString())
    private fun isValidHeight(): Boolean {
        if (height.isNullOrEmpty()) return false
        if (getHeightType() == "cm" && getHeightValue() in 150..193) return true
        if (getHeightType() == "in" && getHeightValue() in 59..76) return true
        return false
    }
}

class PassportBuilder {
    companion object {
        fun buildRawPassport(input: String): RawPassport {
            val keys = input.split(" ").filter { it != "" }
                .map { it.split(":")[0] to it.split(":")[1] }.toMap()
            return RawPassport(
                birthYear = keys["byr"],
                issueYear = keys["iyr"],
                expirationYear = keys["eyr"],
                height = keys["hgt"],
                hairColour = keys["hcl"],
                eyeColour = keys["ecl"],
                pid = keys["pid"],
                cid = keys["cid"]
            )
        }
    }
}

fun String?.isNotNull() = this != null
fun String?.toIntOrDefault(default: Int = 0) = this?.toIntOrNull() ?: default

