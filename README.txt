=== How to run the code ===

Ryan

=== High level description ===

There are databases for adverts, students, employers, and the course 
coordinator. These are stored in the .database files at the root directory
of the given archive. There are separate components for handling login,
admin tasks such as approving advertisements, the advertisement stores

=== Installing own users ===

To install your own users, create a variable like so:

"UserDatabase ub = UserDatabase.getInstance();"

This will give you access to methods such as:

addEmployer(Employer e)

updateStudent(Student student) which will also add a student if it
doesn't already exist

changeCourseCoordinator(String username, String password) which will
overwrite the old course coordinator.

Further information at uk.ac.glasgow.internman.impl.databases;

=== Installing own advertisements ===

To install your own advertisements, create a variable like so:

"AdManager am = AdManager.getInstance();@

This will give you access to methods such as:

createAdvertisement(Employer e, String applicationDetails);

reviseAdvertisement(Integer index, Advertisement revise);

Further information at uk.ac.glasgow.internman.impl.adManager;