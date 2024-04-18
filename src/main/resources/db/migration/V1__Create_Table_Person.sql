CREATE TABLE `person` (
                          `id` bigint NOT NULL,
                          `first_name` varchar(255) DEFAULT NULL,
                          `last_name` varchar(255) DEFAULT NULL,
                          `age` int DEFAULT NULL,
                          `gender` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`)
)
