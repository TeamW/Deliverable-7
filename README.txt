=== How to run the code ===

Execute build/build.xml with all targets in written order. Run will finally run the UI.

=== Contact information ===

We can be contacted via the unofficial "Computing Science Level 3"
Facebook group. Our matriculation numbers, for contact via University
email are below:

Gordon Reid - 1002536r
Ryan Wells - 1002253w
Kris Stewart - 1007175s
David Selkirk - 1003646s
James Gallagher - 0800899g

=== High level description ===

There are databases for adverts, students, employers, and the course 
coordinator. These are stored in the .database files at the root directory
of the given archive. There are separate components for handling login,
admin tasks such as approving advertisements, the advertisement store, and
the user stores.

The .database files are the result of serialisation as all users, adverts, and
associated objects implement the Serializable interface.

=== Basic troubleshooting ===

In the event of any issue, the first thing to do is remove the databases.
The .database files can be safety removed and will be recreated blank on
next execution.

This should resolve most issues (from own experience during development).
If an issue persists, please contact us via the methods specified above.

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