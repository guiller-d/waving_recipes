-- MySQL dump 10.13  Distrib 8.0.20, for macos10.15 (x86_64)
--
-- Host: localhost    Database: cookingRecipe
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredient` (
  `recipe_id` int(11) NOT NULL,
  `ingredient` varchar(2000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` VALUES (1,' 1 cup sour cream\n                     2 tablespoons Frank’s Red Hot® Sauce\n                     1 lime, juiced\n                     1 lime, zested\n                     1 teaspoon kosher salt\n                     1 ½ lb ground chicken\n                     ¼ cup red bell pepper minced\n                     2 tablespoons Frank’s RedHot® Sauce\n                     1 teaspoon garlic powder\n                     1 teaspoon onion powder\n                     3 teaspoons kosher salt\n                     2 tablespoons canola oil\n                     4 slices pepper jack cheese\n                     4 brioche burger buns,\n                     4 toasted butter lettuce leaf\n                     1 avocado, thinly sliced pickled chilli\n        '),(2,' 2 (6-ounce) cans solid, water packed tuna\n                     ½ cup mayonnaise, preferably homemade\n                     ½ cup finely diced celery\n                     3 tablespoons finely diced red onion\n                     3 tablespoons finely minced red bell pepper\n                     2 tablespoons drained capers\n                     2 teaspoons lemon juice\n                     2 teaspoons Kosher salt and freshly ground black pepper\n        '),(3,' 1 1/2 cups jasmine rice\n                     1 cup kimchi, coarsely chopped\n                     2 tablespoons juices reserved\n                     3 tablespoons reduced sodium soy sauce\n                     1 tablespoon gochujang (Korean red pepper paste)\n                     1 tablespoon sesame oil\n                     2 tablespoons canola oil\n                     2 cloves garlic, minced\n                     1 small onion, diced\n                     2 teaspoons freshly grated ginger\n                     1 (3.5-ounce) package shiitake mushrooms, sliced\n                     1 cup matchstick carrots\n                     1/2 bunch kale, stems removed and leaves chopped\n                     4 fried eggs\n                     1/4 cup nori strips\n                     2 green onions, thinly sliced\n                     1 tablespoon black sesame seeds\n        '),(4,' 4 large potatoes, peeled and cut into strips\n                     1 cup all-purpose flour\n                     1 teaspoon baking powder\n                     1 teaspoon salt\n                     1 teaspoon ground black pepper\n                     1 cup milk\n                     1 egg\n                     1 quart vegetable oil for frying\n                     1 ½ pounds cod fillets\n        '),(5,' 4 large eggs\n                     ¼ cups of milk\n                     1 Pinch of salt,\n                     1 Pinch of pepper,\n                     2 tablespoon of butter\n        '),(6,' 1 cup basmati rice\n                     1 1/2 tablespoons canola oil\n                     1 1/2 pounds boneless, skinless chicken thighs, cut into 1-inch chunks\n                     2 teaspoon Kosher salt and freshly ground black pepper\n                     1/2 medium sweet onion, diced\n                     3 tablespoons tomato paste\n                     3 cloves garlic, minced\n                     1 tablespoon freshly grated ginger\n                     1 1/2 teaspoons garam masala\n                     1 1/2 teaspoons chili powder\n                     1 1/2 teaspoons ground turmeric\n                     1 (15-ounce) can tomato sauce\n                     1 cup chicken stock\n                     1/2 cup heavy cream\n                     2 tablespoons chopped fresh cilantro leaves\n        '),(7,' 2 tbsp. extra-virgin olive oil\n                     3 chicken breasts (about 1 1/2 lb.)\n                     1 teaspoon of Kosher salt\n                     1 teaspoon of Freshly ground black pepper\n                     2 tbsp. sesame oil\n                     1 medium onion, chopped\n                     2 carrots, peeled and diced\n                     3 cloves garlic, minced\n                     1 tbsp. freshly minced ginger\n                     4 cups of cooked white rice (preferably leftover)\n                     3/4 cups of frozen peas\n                     3 large eggs, beaten\n                     3 tbsp. low-sodium soy sauce\n                     2 green onions, thinly sliced\n        '),(8,' 500 grams chicken\n                     4 cups basmati rice\n                     3 onions (sliced thin)\n                     4 tomatoes (chopped fine)\n                     4 green chilies, slit\n                     3 tsp ginger-garlic paste\n                     2 tbsp red chili powder\n                     2 tbsp coriander powder\n                     1 tbsp garam masala powder\n                     1/2 cup coriander leaves (finely chopped)\n                     1/2 cup mint leaves (finely chopped)\n                     3 tbsp oil\n                     1 tbsp ghee and salt\n                     1 Orange food grade colors (optional)\n                     2 tsp curd\n                     1 tbsp red chili powder\n                     1 tsp turmeric powder\n                     1 tsp salt\n        '),(9,' 3 cups shredded Rotisserie chicken\n                     1 Tablespoon chili powder\n                     1 tsp EACH ground cumin, garlic powder\n                     1/2 tsp EACH onion powder, smoked paprika dried oregano, salt\n                     1/4 teaspoon cayenne pepper or more to taste\n                     1 14.5 oz. can fire roasted diced tomatoes, (may use regular)\n                     1/4 cup water or low sodium chicken broth\n                     2 tablespoons mild chopped green chiles (from 4 oz. can green chiles)\n                     2 tablespoons tomato paste	For Serving (pick your favs!)\n                     Optional Side Corn, flour tortillas, cheese, black beans\n        '),(10,'chicken wings, flour, olive oil, salt and pepper.');
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-02 19:44:11
