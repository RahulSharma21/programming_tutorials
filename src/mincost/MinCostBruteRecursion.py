import time
import functools

@functools.lru_cache(maxsize=None)
def solve(target_energy, cost, num, energy):

    if target_energy == 0:
        return 0

    min_cost = -1

    for i in range(len(energy)):

        if target_energy >= energy[i] and num[i] > 0:

            list1 = list(num)

            list1[i] = list1[i] - 1

            cst = solve(target_energy - energy[i], cost, tuple(list1), energy)

            if cst != -1 and (min_cost == -1 or min_cost > cst + cost[i]):
                min_cost = cst + cost[i]

    return min_cost

start_time=time.time()

cost = (100, 20, 10)
# num = (5, 5, 5)

num = (10000, 10000, 10000)

energy = (5, 3, 2)

max_target_energy = 100

for target_energy in range(2,max_target_energy + 1):
    min_cost = solve(target_energy, cost, num, energy)
    print('target = ', target_energy, ' cost = ', min_cost)

print("--- %s seconds ---" % (time.time() - start_time))
